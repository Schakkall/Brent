package enem;

public class TesteManipulador {

	public static void main(String[] args) {
		
		String path = "F:\\WorkSpace\\Brent\\aluno.db";
		///*
		//Passo1 - Construção do arquivo base
		IFileOrganizer arq1 = new ManipuladorSequencial(path);
		
		for (int i=10 ; i>=0; i--){
		  	Aluno a = new Aluno(0, "Aluno"+i, "Rua "+i, (short)00, "M", "aluno"+i+"@ufs.br");
		  	arq1.addReg(a);
		}
		
		//Passo2 - Demonstração do método de Brent [Exemplo do Slide Show - Página 67]
		arq1 = new OrganizadorBrent(path);
		Aluno e;

		e = new Aluno(27,"#27#","*******",(short)00,"M","aluno27@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(18,"#18#","*******",(short)00,"M","aluno18@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(29,"#29#","*******",(short)00,"M","aluno29@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(28,"#28#","*******",(short)00,"M","aluno28@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(39,"#39#","*******",(short)00,"M","aluno39@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(13,"#13#","*******",(short)00,"M","aluno13@ufs.com.br");
		arq1.addReg(e);
		e = new Aluno(16,"#16#","*******",(short)00,"M","aluno16@ufs.com.br");
		arq1.addReg(e);
		
		arq1.delReg(27);
		//*/
		//IFileOrganizer arq1 = new OrganizadorBrent(path);
		//System.out.println(arq1.getReg(29));

	}

}
