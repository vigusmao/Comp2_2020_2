public enum NivelUsuario {

    INICIANTE,
    SENIOR(100),
    NINJA(500);

    private int minTuites;

    NivelUsuario(int minTuites) {
        this.minTuites = minTuites;
    }

    NivelUsuario() {
        this(0);
    }

    public int getMinTuites() {
        return minTuites;
    }
}
