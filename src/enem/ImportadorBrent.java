package enem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ImportadorBrent extends Tester {
	
	public static void main(String[] args) throws IOException {
		
		String pathAleat = "C:\\bd\\enem_aleat.db";
		String pathBrent = "C:\\bd\\enem_brent.db";
		
		IFileOrganizer brtOrganizer = new OrganizadorBrent(pathBrent);
		
		File sFile = new File(pathAleat);
		RandomAccessFile sourceFile = new RandomAccessFile(sFile, "r");
		FileChannel sourceChannel = sourceFile.getChannel();
		
		long upperBound = sourceChannel.size() / Aluno.RECORD_SIZE;
		long ini, end;
		
		ini = System.currentTimeMillis();
		
		for (long i = 0; i < upperBound; i++) {
			ByteBuffer sourceAluno = ByteBuffer.allocate(Aluno.RECORD_SIZE);
			sourceAluno.position(0);
			sourceChannel.read(sourceAluno, i * Aluno.RECORD_SIZE);
			sourceAluno.flip();
			brtOrganizer.addReg(new Aluno(sourceAluno));
		}
		
		end = System.currentTimeMillis();
		
		printAnalysis(ini, end, "leitura de registros organizados aletoriamente e inserção dos mesmos segundo o método de Brent");
		
		sourceFile.close();
		
	}	

}