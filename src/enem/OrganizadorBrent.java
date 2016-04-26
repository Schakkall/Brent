package enem;
/**
 * Classe que implementa o m�todo de Brent para organiza��o
 * de arquivos de alunos em disco.
 * 
 * @author  Simone Ris Santos Silva
 * @author  J. Eurique C. Ribeiro Jr
 * @author  Leonardo de Jesus Silva
 */

public class OrganizadorBrent implements IFileOrganizer {
	
	private static long MAX_SIZE = 12000017;
	
	private long Hash(int key) {
		return 0;
	}
	
	private long Inc(int key) {
		return (key % (MAX_SIZE - 2) + 1);
	} 
	
	public static void main(String[] args) {
		System.out.println("Hello World!"); 
	}

	@Override
	public boolean addReg(Aluno p) {
		// TODO Auto-generated method stub
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
}
