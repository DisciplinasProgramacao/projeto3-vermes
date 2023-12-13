
/**
 * Classe que implementa a interface UsoDeVagaFactory para criar um Turnista.
 *  
 */
public class TurnistaFactory implements UsoDeVagaFactory {
    private final Turno turno;

    public TurnistaFactory(Turno turno) {
        this.turno = turno;
    }

    @Override
    /**
     * Método que cria um objeto do tipo Turnista.
     * @param vaga Vaga que será ocupada pelo objeto criado.
     * @return Objeto do tipo Turnista.
     * @throws VagaIndisoponivelException Exceção lançada quando a vaga passada como parâmetro já está ocupada.
     */
     
    public UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Turnista(vaga, turno);
    }
}
