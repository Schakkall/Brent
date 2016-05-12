package enem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Classe que implementa o método de organização
 * sequencial para registros de alunos em arquivos.
 * 
 * @author  Simone Ris Santos Silva
 * @author  J. Eurique C. Ribeiro Jr
 * @author  Leonardo de Jesus Silva
 */


public class ManipuladorSequencial implements IFileOrganizer {

	private static final int RECORD_SIZE = Aluno.RECORD_SIZE;

	private FileChannel channel;

	public ManipuladorSequencial(String path) {
		try {
			File file = new File(path);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			this.channel = raf.getChannel();
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um exceção ao tentar abrir o arquivo | public ManipuladorSequencial(String path)\n"+e);
			e.printStackTrace();
		}

	}
	
	Aluno readAluno(long index) throws IOException {
		if ((index < 0) || (index > this.channel.size()))
			return null;// Out of bounds

		ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);
		
		this.channel.read(buffer, index);
		buffer.flip();

		return new Aluno(buffer);
	}

	private long binarySearch(int matric) throws IOException {
		long low = 0;
		long high = (this.channel.size() / RECORD_SIZE);
		long mid = 0;

		while (low <= high) {	
			mid = (low + high) / 2;
			Aluno aluno = readAluno(mid * RECORD_SIZE);
			
			if (aluno == null)
				return -1;
			
			if (aluno.getMatricula() < matric)
				low = mid + 1;
			else if (aluno.getMatricula() > matric)
				high = mid - 1;
			else
				return mid;			
		}
		return -1;
	}
	
	@Override
	public boolean addReg(Aluno aluno) {
		try {
			ByteBuffer record = aluno.getBuffer();

			this.channel.position(0);

			if (this.channel.size() == 0) {
				this.channel.write(record, 0);
			} else {
				for (long i = 0; i < this.channel.size(); i += RECORD_SIZE) {
					Aluno bufferAluno = readAluno(i);

					if (bufferAluno.getMatricula() >= aluno.getMatricula()) {					
						for (long j = i; j < this.channel.size(); j += RECORD_SIZE) {
						
							bufferAluno = readAluno(j);												

							this.channel.write(record, j );
							record = bufferAluno.getBuffer();
							
						}
					
						break;
					}	

				}
				this.channel.write(record, this.channel.size());
			}
			return true;
		} catch (IOException e) {
			//TODO Tratar IOException	
		}
		return false;
	}

	@Override
	public Aluno delReg(int matric) {
		try {
			long newSize = channel.size() - RECORD_SIZE;
			this.channel.position(0);

			for (long i = 0; i < channel.size(); i += RECORD_SIZE) {
				Aluno aluno = readAluno(i);
				
				if (aluno.getMatricula() == matric) {
					for (long j = i + RECORD_SIZE; j < channel.size(); j += RECORD_SIZE) {
						ByteBuffer bufb = ByteBuffer.allocate(RECORD_SIZE);

						channel.read(bufb, j);
						bufb.flip();
						channel.write(bufb, j - RECORD_SIZE);
					}
					channel.truncate(newSize);
					break;
				}
			}
		} catch (IOException e) {
			//TODO Tratar IOException
		}
		return null;
	}

	@Override
	public Aluno getReg(int matric) {
		try {
			this.channel.position(0);
			for (long i = 0; i < channel.size(); i += RECORD_SIZE) {
				Aluno aluno = readAluno(i);
				if (aluno.getMatricula() == matric) {
					return aluno;
				}
			}
		} catch (IOException e) {
			//TODO Tratar IOException
		}
		return null;		
	}

	
	public Aluno getRegBin(int matric) {
		try {
			this.channel.position(0);
			
			long index;

			index = this.binarySearch(matric);

			if (index == -1)
				return null;
			else
				return readAluno(index * RECORD_SIZE);
		} catch (Exception e) {
			System.out.println(e);
			//TODO Tratar IOException
		}
		return null;
	}	

	
	
}
