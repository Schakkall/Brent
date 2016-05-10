package enem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TesteBuscaManipuladorSequencial {
		
	public static void main(String[] args) throws IOException {

		String pathOrigem = "C:\\bd\\selected.db";
		String pathBrent = "C:\\bd\\enem_seq.db";

		ManipuladorSequencial arq1 = new ManipuladorSequencial(pathBrent);
		
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
	
	public static void printAnalysis(long iniTime, long endTime, String methodName) {
		long total, min, hor;
		
		total = (iniTime - endTime);
		System.out.println("Análise de tempo da "+ methodName +"\n");
		System.out.println("Tempo total em milisegundos :"+ total);
		min = total / 60000;
		System.out.println("Tempo total em minutos :"+ min);
		hor = min / 60;
		System.out.println("Tempo de processamento das buscas (em horas) :"+ hor +"\n\n");		
		
	}
		
	
}


