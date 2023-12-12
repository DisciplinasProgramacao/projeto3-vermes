public class MensalistaFactory implements UsuarioFactory {
    @Override
    public Usuario criar(Vaga vaga) throws VagaIndisoponivelException {
        return new Mensalista(vaga);
    }
}
