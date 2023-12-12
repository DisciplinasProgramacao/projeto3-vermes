public class TurnistaFactory implements UsoDeVagaFactory {
    private final Turno turno;

    public TurnistaFactory(Turno turno) {
        this.turno = turno;
    }

    @Override
    public UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Turnista(vaga, turno);
    }
}
