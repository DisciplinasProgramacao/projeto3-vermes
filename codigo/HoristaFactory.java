public class HoristaFactory implements UsuarioFactory {
    @Override
    public Usuario criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Horista(vaga);
    }
}
