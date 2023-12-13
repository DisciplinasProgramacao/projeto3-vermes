
 
 /** 
  * Classe que implementa a interface UsoDeVagaFactory para criar um objeto do tipo Horista.
  */
public class HoristaFactory implements UsoDeVagaFactory {
    /**
    * Método que cria um objeto do tipo Horista.
    * @param vaga Vaga que será ocupada pelo objeto criado.
    * @return Objeto do tipo Horista.
    * @throws VagaIndisoponivelException Exceção lançada quando a vaga passada como parâmetro já está ocupada.
     */
    @Override
    public UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Horista(vaga);
    }
}
