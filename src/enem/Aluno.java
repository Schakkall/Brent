package enem;

import java.nio.ByteBuffer;

public class Aluno {


	private static final short 
	LNOME = 50, 
	LENDERECO = 60, 
	LSEXO = 1, 
	LEMAIL = 40;
	
	public static final short
	RECORD_SIZE = 157;  


	private int    matricula; // 04 bytes  
	private String nome;      // 50 bytes 
	private String endereco;  // 60 bytes 
	private short  idade;     // 02 bytes 
	private String sexo;      // 01 byte  
	private String email;     // 40 bytes 


	public Aluno(){
		this.matricula = 0;

		String str = "";

		this.nome      = this.redimensionar(str, LNOME);
		this.endereco  = this.redimensionar(str, LENDERECO);
		this.idade     = 0;
		this.sexo      = this.redimensionar(str, LSEXO);
		this.email     = this.redimensionar(str, LEMAIL);
	}
	
	public Aluno(int matricula, String nome){
		this();
		this.matricula = matricula;
		this.nome = this.redimensionar(nome, LNOME);
	}
	

	public Aluno(int matricula, String nome, String endereco, short idade, String sexo, String email){
		this.matricula = matricula;
		this.nome      = this.redimensionar(nome, LNOME);
		this.endereco  = this.redimensionar(endereco, LENDERECO);
		this.idade     = idade;
		this.sexo      = this.redimensionar(sexo, LSEXO);
		this.email     = this.redimensionar(email, LEMAIL);
	}

	public Aluno(ByteBuffer buf) {
		byte[] bufStr;

		this.matricula = buf.getInt();

		bufStr = new byte[LNOME];
		buf.get(bufStr);
		this.nome = new String(bufStr);
		
		this.idade = buf.getShort();		

		bufStr = new byte[LENDERECO];
		buf.get(bufStr);
		this.endereco = new String(bufStr);

		bufStr = new byte[LSEXO];
		buf.get(bufStr);
		this.sexo = new String(bufStr);

		bufStr = new byte[LEMAIL];
		buf.get(bufStr);
		this.email = new String(bufStr);		
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = this.redimensionar(nome, LNOME);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = this.redimensionar(endereco, LENDERECO);
	}

	public short getIdade() {
		return idade;
	}

	public void setIdade(short idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = this.redimensionar(sexo, LSEXO);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = this.redimensionar(email, LEMAIL);
	}

	private String redimensionar(String str, int tam){		
		int dif = tam - str.length();

		for (int i = 0; i < dif; i++) 
			str += " ";
		
		return str.substring(0,tam); 
	}

	public ByteBuffer getBuffer(){
		ByteBuffer buf = ByteBuffer.allocate(RECORD_SIZE);

		buf.putInt(this.matricula);
		buf.put(this.nome.getBytes());
		buf.putShort(this.idade);
		buf.put(this.endereco.getBytes());
		buf.put(this.sexo.getBytes());
		buf.put(this.email.getBytes());

		buf.flip();

		return buf;
	}
	
	public boolean equals(Aluno aluno) {
		if (this.matricula == aluno.matricula)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "Matricula:" + Integer.toString(this.matricula) + "\n" +
			   "Nome     :" + this.nome                        + "\n" +
			   "Idade    :" + Integer.toString(this.idade)     + "\n" +				
			   "Endereço :" + this.endereco                    + "\n" +
			   "Sexo     :" + this.sexo                        + "\n" +
			   "E-mail   :" + this.email                       ;
	}


}