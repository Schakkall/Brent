package enem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TesteBuscaOrganizadorBrent extends Tester{

	
	public static void main(String[] args) throws IOException {

		String pathOrigem = "C:\\bd\\selected.db";
		String pathBrent = "C:\\bd\\enem_brent.db";

		OrganizadorBrent arq1 = new OrganizadorBrent(pathBrent);
		
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
		
		// Análise de desempenho para busca
		ini = System.currentTimeMillis();

		for (i = 0; i < 1000; i++) {
			Aluno c = arq1.getReg(matriculas[i]);
			System.out.println(c);
		}

		fim = System.currentTimeMillis();

		printAnalysis(ini, fim, "busca em Método de Brent");
		
		fileOrigem.close();

	}	
		
}
