import java.time.LocalTime;

/**
 * A enumeração `Turno` representa diferentes turnos ao longo do dia,
 * cada um definido por um horário de início e um horário de fim.
 */
public enum Turno {

    /** Turno da manhã. */
    MANHA(LocalTime.of(8, 0), LocalTime.of(12, 0)),

    /** Turno da tarde. */
    TARDE(LocalTime.of(12, 1), LocalTime.of(18, 0)),

    /** Turno da noite. */
    NOITE(LocalTime.of(18, 1), LocalTime.of(23, 59));

    /** O horário de início do turno. */
    private LocalTime inicio;

    /** O horário de fim do turno. */
    private LocalTime fim;

    /**
     * Cria um novo objeto `Turno` com os horários de início e fim fornecidos.
     *
     * @param inicio O horário de início do turno.
     * @param fim O horário de fim do turno.
     */
    Turno(LocalTime inicio, LocalTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Verifica se a hora especificada está dentro do horário do turno.
     *
     * @param hora O horário a ser verificado.
     * @return true se a hora estiver dentro do horário do turno, false caso contrário.
     */
    public boolean eHorarioDoTurno(LocalTime hora) {
        return hora.isAfter(inicio) && hora.isBefore(fim);
    }
}
