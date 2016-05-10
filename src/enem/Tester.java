package enem;

public abstract class Tester {
	
	public static void printAnalysis(long iniTime, long endTime, String methodName) {
		long total, min, hor;
		
		total = (endTime - iniTime);
		System.out.println("Análise de tempo da "+ methodName +"\n");
		System.out.println("Tempo total em milisegundos :"+ total);
		min = total / 60000;
		System.out.println("Tempo total em minutos :"+ min);
		hor = min / 60;
		System.out.println("Tempo de processamento das buscas (em horas) :"+ hor +"\n\n");		
		
	}


}
