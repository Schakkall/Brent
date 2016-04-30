//package aula04;
package enem;


import java.io.IOException;

public class TesteManipulador {

	public static void main(String[] args) throws IOException {
		//IFileOrganizer arg = new ManipuladorSimples ("/home/aluno/Desktop/aluno.db");
		//
		IFileOrganizer arq2 = new ManipuladorSequencial("F:\\WorkSpace\\Brent\\aluno.db");
		
		for (int i=0 ; i<=10;i++){
		  	Aluno a = new Aluno(0, "Aluno"+i, "Rua "+i, (short)19, "F", "aluno"+i+"@ufs.br");
		  	arq2.addReg(a);
		}

		
		
		/*
		Aluno e = new Aluno(0,"#0000#","*******",(short)00,"M","b@b.com.br");
		arq2.addReg(e);
		*/
		/*
		Aluno a = new Aluno(1,"#1111#","*******",(short)20,"F","a@a.com.br");
		Aluno b = new Aluno(5,"#5555#","*******",(short)40,"F","a@a.com.br");
	    Aluno c = new Aluno(4,"#4444#","*******",(short)60,"M","b@b.com.br");
	    Aluno d = new Aluno(3,"#3333#","*******",(short)80,"M","b@b.com.br");
	    Aluno e = new Aluno(2,"#2222#","*******",(short)00,"M","b@b.com.br");

			
		arq2.addReg(a);
		arq2.addReg(b);
		arq2.addReg(c);
		arq2.addReg(d);
		arq2.addReg(e);
		*/
		/*
		Aluno b = arq2.getReg(14);
		System.out.println(b.getMatricula());
		System.out.println(b.getNome());
		System.out.println(b.getSexo());
		System.out.println(b.getIdade());
		//*/
		//arq2.delReg(1);
	

	}

}
