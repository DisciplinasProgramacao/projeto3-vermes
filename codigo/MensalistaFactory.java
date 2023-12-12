public class MensalistaFactory implements UsoDeVagaFactory {
    @Override
    public UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Mensalista(vaga);
    }
}
