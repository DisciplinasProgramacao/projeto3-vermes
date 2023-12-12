public interface UsuarioFactory {
    Usuario criar(Vaga vaga) throws VagaIndisoponivelException;
}
