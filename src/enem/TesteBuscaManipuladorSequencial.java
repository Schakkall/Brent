package enem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TesteBuscaManipuladorSequencial extends Tester{
		
	public static void main(String[] args) throws IOException {

		String pathOrigem = "C:\\bd\\selected.db";
		String pathSequen = "C:\\bd\\enem_seq.db";

		ManipuladorSequencial arq1 = new ManipuladorSequencial(pathSequen);
		
		File fOrigem = new File(pathOrigem);
		RandomAccessFile fileOrigem = new RandomAccessFile(fOrigem, "r");
		FileChannel channelOrigem = fileOrigem.getChannel();
		
		int i;
		int[] matriculas;
		matriculas = new int[1000];

		for (i = 0; i < 1000; i++) {
			ByteBuffer mat = ByteBuffer.allocate(4);
			mat.position(0);
			channelOrigem.read(mat, i * 4);
			mat.flip();
			matriculas[i] = mat.getInt();			
		}

		long ini, fim;
		
		// Analise de desempenho para busca de forma binária
		ini = System.currentTimeMillis();		

		for (i = 0; i < 1000; i++) {
			Aluno c = arq1.getRegBin(matriculas[i]); 
		}

		fim = System.currentTimeMillis();
		
		
		printAnalysis(ini, fim, "busca binária em Organização Sequencial");

		// Analise de desempenho para busca de forma sequencial
		ini = System.currentTimeMillis();

		for (i = 0; i < 1000; i++) {
			Aluno c = arq1.getReg(matriculas[i]);
		}

		fim = System.currentTimeMillis();

		printAnalysis(ini, fim, "busca sequencial em Organização Sequencial");
		
		fileOrigem.close();

	}	
			
	
}


