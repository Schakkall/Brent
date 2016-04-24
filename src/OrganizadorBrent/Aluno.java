package OrganizadorBrent;

import java.nio.ByteBuffer;

public class Aluno {
	

	private static final short 
	  LIMIT_NOME = 50, 
	  LIMIT_ENDERECO = 60, 
	  LIMIT_SEXO = 1, 
	  LIMIT_EMAIL = 40,
	  BUFFER_SIZE = 157;  
	
	private int    matricula; //04 bytes
	private String nome;      //50 bytes
	private String endereco;  //60 bytes
	private short  idade;     //02 bytes
	private String sexo;      //01 byte
	private String email;     //40 bytes 
	                            
	
	public Aluno(){
		this.matricula = 0;
		
		String str = "";
		
		this.nome      = this.corrigir(str, LIMIT_NOME);
		this.endereco  = this.corrigir(str, LIMIT_ENDERECO);
		this.idade     = 0;
		this.sexo      = this.corrigir(str, LIMIT_SEXO);
		this.email     = this.corrigir(str, LIMIT_EMAIL);
	}
	
	public Aluno( int matricula, String nome, String endereco, short idade, String sexo, String email){
		this.matricula = matricula;
		this.nome      = this.corrigir(nome, LIMIT_NOME);
		this.endereco  = this.corrigir(endereco, LIMIT_ENDERECO);
		this.idade     = idade;
		this.sexo      = this.corrigir(sexo, LIMIT_SEXO);
		this.email     = this.corrigir(email, LIMIT_EMAIL);
	}
	
	public Aluno(ByteBuffer buf) {
		
		byte[] bufStr;
			
		this.matricula = buf.getInt();
		
		bufStr = new byte[LIMIT_NOME];
		buf.get(bufStr);
		this.nome = new String(bufStr);
		
		bufStr = new byte[LIMIT_ENDERECO];
		buf.get(bufStr);
		this.endereco = new String(bufStr);
		
		this.idade = buf.getShort();
		
		bufStr = new byte[LIMIT_SEXO];
		buf.get(bufStr);
		this.sexo = new String(bufStr);
		
		bufStr = new byte[LIMIT_EMAIL];
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
		this.nome = this.corrigir(nome, LIMIT_NOME);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = this.corrigir(endereco, LIMIT_ENDERECO);
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
		this.sexo = this.corrigir(sexo, LIMIT_SEXO);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = this.corrigir(email, LIMIT_EMAIL);
	}
	
	private String corrigir(String str, int tam){		
		int dif = tam - str.length();
		
		for (int i = 0; i < dif; i++) {
			str += " ";
		}

		str = str.substring(0,tam);		
		
		return str; 
	}

	public ByteBuffer getBuffer(){
		ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
	  
		buf.putInt(this.matricula);
		buf.put(this.nome.getBytes());
		buf.putShort(this.idade);
		buf.put(this.endereco.getBytes());
		buf.put(this.sexo.getBytes());
		buf.put(this.email.getBytes());
	  
		buf.flip();
	  
		return buf;
	}
	

}
