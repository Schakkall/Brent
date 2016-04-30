package enem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ManipuladorSequencial implements IFileOrganizer {

	private static final int RECORD_SIZE = 157;

	private FileChannel canal;

	public ManipuladorSequencial(String path) throws FileNotFoundException {

		File file = new File(path);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		this.canal = raf.getChannel();

	}

	private Aluno readAluno(long index) throws IOException {

		if ((index < 0) || (index > this.canal.size()))
			return null;// Out of bounds

		ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);

		this.canal.read(buffer, index);
		buffer.flip();

		return new Aluno(buffer);
	}

	private long binarySearch(int matric) throws IOException {
		long low = 0;
		long high = (this.canal.size() / RECORD_SIZE);
		long mid = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			Aluno aluno = readAluno(mid * RECORD_SIZE);

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

		this.canal.position(0);

		if (this.canal.size() == 0) {
			this.canal.write(record, 0);
		} else {
			
			for (int i = 0; i < this.canal.size(); i += RECORD_SIZE) {

				Aluno bufferAluno = readAluno(i);

				if (bufferAluno.getMatricula() >= aluno.getMatricula()) {
					
					for (int j = i; j < this.canal.size(); j += RECORD_SIZE) {
						
						bufferAluno = readAluno(j);												

						this.canal.write(record, j );
						record = bufferAluno.getBuffer();
						
					}
					
					break;
				}	

			}
			this.canal.write(record, this.canal.size());
		}
		
		return false;
		} catch (IOException e) {
			
		}
		return false;
	}

	@Override
	public Aluno delReg(int matric) {
		try {
		
		int newSize = (int) (canal.size() - RECORD_SIZE);
		this.canal.position(0);

		for (int i = 0; i < canal.size(); i += RECORD_SIZE) {

			Aluno aluno = readAluno(i);

			if (aluno.getMatricula() == matric) {

				for (int j = i + RECORD_SIZE; j < canal.size(); j += RECORD_SIZE) {

					ByteBuffer bufb = ByteBuffer.allocate(RECORD_SIZE);

					canal.read(bufb, j);
					bufb.flip();
					canal.write(bufb, j - RECORD_SIZE);

				}

				canal.truncate(newSize);
				break;

			}
		}
		} catch (IOException e) {
			
		}
		
		return null;
	}

	@Override
	public Aluno getReg(int matric) {
		try {
		this.canal.position(0);
		for (int i = 0; i < canal.size(); i += RECORD_SIZE) {
			Aluno aluno = readAluno(i);
			if (aluno.getMatricula() == matric) {
				return aluno;
			}
		}
		return null;
		} catch (IOException e) {
			
		}
		return null;
		
	}

	
}