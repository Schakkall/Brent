package enem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Classe que implementa o método de Brent para organização
 * de arquivos de alunos em disco.
 * 
 * @author  Simone Ris Santos Silva
 * @author  J. Eurique C. Ribeiro Jr
 * @author  Leonardo de Jesus Silva
 */

public class OrganizadorBrent implements IFileOrganizer {
	
	private static long P = 11;
	private static int  RECORD_SIZE = 157;
	
	private FileChannel canal;
	
	public OrganizadorBrent(String path) throws FileNotFoundException {
		File file = new File(path);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		this.canal = raf.getChannel();
	}
	
	private long hash(long key) {
		return (key % P);
	}
	
	private long inc(long key) {
		return ((key / P) % P);
	}

	private Aluno readAluno(long index) throws IOException {
		if ((index < 0) || (index > this.canal.size()))
			return null;// Out of bounds

		ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);

		this.canal.read(buffer, index * RECORD_SIZE);
		buffer.flip();

		return new Aluno(buffer);
	}	
	
	private void simpleInsert(Aluno p, long pos) throws IOException {
		ByteBuffer record = p.getBuffer();
		
		this.canal.position(0);
		this.canal.write(record, pos * RECORD_SIZE);
	}
	
	private Cost calcCusto(Aluno p) throws IOException {
		long pos = this.hash(p.getMatricula());
		long i   = this.inc(p.getMatricula());
		long c   = 1;
				
		while (!this.isEmpty(pos)) {
			pos = this.hash(pos+i);
			c   += 1;
		}
		
		return new Cost(pos,c);
	}
	
	
	private void solveCollision(Aluno p, long pos) throws IOException {
		Cost a = this.calcCusto(p);
		Cost b = this.calcCusto(readAluno(pos));
		
		if (a.getAccessCost() > b.getAccessCost())
			this.simpleInsert(p, b.getPosition());
		else
			this.simpleInsert(p, a.getPosition());
		
	}
	
	private boolean isEmpty(long index) throws IOException {
		Aluno a = this.readAluno(index);
		if ((a.getMatricula() == 0) || (a.getMatricula() == -1))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean addReg(Aluno p) {
		long pos = this.hash(p.getMatricula());
		
		try {
			if (this.isEmpty(pos) )
				this.simpleInsert(p, pos);
			else
				this.solveCollision(p, pos);
		} catch (IOException e) {
			// TODO 
			e.printStackTrace();
		}
			
		return false;
	}

	@Override
	public Aluno getReg(int matric) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno delReg(int matric) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hello World!");
		
		OrganizadorBrent O = new OrganizadorBrent("F:\\WorkSpace\\Brent\\aluno.db");
		Aluno e = new Aluno(21,"#2222#","*******",(short)00,"M","b@b.com.br");
		O.addReg(e);
		
		/*
		try {
			System.out.println(O.calcCusto(e));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
	}

}
