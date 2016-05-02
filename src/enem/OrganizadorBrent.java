package enem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Classe que implementa o método de Brent para organização
 * direta de registros de alunos em arquivos.
 * 
 * @author  Simone Ris Santos Silva
 * @author  J. Eurique C. Ribeiro Jr
 * @author  Leonardo de Jesus Silva
 */

public class OrganizadorBrent implements IFileOrganizer {
	
	private static final int RECORD_SIZE = Aluno.RECORD_SIZE;
	private static final long P = 11;
	
	private FileChannel channel;
	
	public OrganizadorBrent(String path) {
		try {
			File file = new File(path);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			this.channel = raf.getChannel();
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um exceção ao tentar abrir o arquivo | public OrganizadorBrent(String path)\n"+e);
			e.printStackTrace();
		}
	}
	
	private long hash(long key) {
		return (key % P);
	}
	
	private long inc(long key) {
		return ((key / P) % P);
	}

	private Aluno readAluno(long index) throws IOException {
		if ((index < 0) || (index > this.channel.size()))
			return null;// Out of bounds
		
		ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);

		this.channel.read(buffer, index * RECORD_SIZE);
		buffer.flip();

		return new Aluno(buffer);
	}	
	
	private void simpleInsert(Aluno p, long pos) throws IOException {
		ByteBuffer record = p.getBuffer();
		
		this.channel.position(0);
		this.channel.write(record, pos * RECORD_SIZE);
	}
	
	private Cost calcCost(Aluno p) throws IOException {
		long pos = this.hash(p.getMatricula());
		long i   = this.inc(p.getMatricula());
		long c   = 1;
				
		while (!this.isEmpty(pos)) {
			pos = this.hash(pos + i);
			c   += 1;
		}
		
		return new Cost(pos,c);
	}
	
	
	private void solveCollision(Aluno p, long pos) throws IOException {
		Aluno alunoAtPos = readAluno(pos);
		
		Cost case1 = this.calcCost(p);
		Cost case2 = this.calcCost(alunoAtPos);
		
		if (case1.getAccessCost() <= case2.getAccessCost())
			this.simpleInsert(p, case1.getPosition());
		else {
			this.simpleInsert(alunoAtPos, case2.getPosition());
			this.simpleInsert(p, pos);
		}	
		
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
		} catch (Exception e) {
			System.out.println("Ocorreu uma exceção ao tentar adicionar o registro | public boolean addReg(Aluno p)\n"+e); 
			e.printStackTrace();
		}
			
		return false;
	}

	@Override
	public Aluno getReg(int matric) {
		long pos = this.hash(matric);
		long i   = this.inc(matric);
		
		long counter = P;
		
		try {
			while ((this.readAluno(pos).getMatricula() != matric) && (--counter >= 0))
				pos = this.hash(pos + i);
			
			if (counter < 0)
				return null;
			else
				return readAluno(pos);
			
		} catch (Exception e) {
			System.out.println("Ocorreu uma exceção ao tentar capturar o registro | public Aluno getReg(int matric)\n"+e); 
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Aluno delReg(int matric) {
		long pos = this.hash(matric);
		long i   = this.inc(matric);
		
		long counter = P;
		
		try {
			while ((this.readAluno(pos).getMatricula() != matric) && (--counter >= 0))
				pos = this.hash(pos + i);
			
			if (counter >= 0)
				this.simpleInsert(new Aluno(-1,"RECORD ERASED"), pos);
			
		} catch (Exception e) {
			System.out.println("Ocorreu uma exceção ao tentar remover o registro | public Aluno delReg(int matric)\n"+e); 
			e.printStackTrace();
		}
		
		return null;
	}
	
}
