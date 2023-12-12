public class HoristaFactory implements UsoDeVagaFactory {
    @Override
    public UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Horista(vaga);
    }
}
