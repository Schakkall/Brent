package OrganizadorBrent;
/**
 * Interface gen�rica que define as opera��es de organizadores
 * de arquivos de alunos em disco.
 * @author Tarcisio Rocha
 */
public interface IFileOrganizer {

	/**
	 * Dada uma inst�ncia da classe Aluno, este m�todo
	 * adiciona os dados da inst�ncia em um arquivo seguindo o
	 * m�todo de organiza��o de arquivos especificado.
	 * @param p Inst�ncia da classe Aluno
	 * @return True se o registro foi adicionado com sucesso;
	 *         False se o registro n�o pode ser adicionado.
	 */
	public boolean addReg(Aluno p);

	/**
	 * Dado um n�mero de matr�cula, este m�todo consulta o arquivo de
	 * alunos e devolve uma inst�ncia que encapsula
	 * aos dados do aluno que cont�m a matr�cula fornecida.
	 * @param matric N�mero de matr�cula para a consulta.
	 * @return Inst�ncia da classe Aluno correspondente
	 *         � matr�cula fornecida;
	 *         Null se a matr�cula informada n�o existe no arquivo.
	 */
	public Aluno getReg(int matric);

	/**
	 * Dado um n�mero de matr�cula, localiza e exclui o registro do
	 * arquivo de alunos que corresponde � matr�cula
	 * fornecida.
	 * @param matric Matr�cula do aluno a ser exclu�do.
	 * @return Inst�ncia com os dados do aluno excluido
	 *         Null se a matr�cula informada n�o existe na base de
	 *         alunos.
	 */
	public Aluno delReg(int matric);
}