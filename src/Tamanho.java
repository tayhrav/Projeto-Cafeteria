public enum Tamanho {
    PEQUENO,
    MEDIO,
    GRANDE;

    @Override
    public String toString() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static Tamanho obterTamanho(String valor) {
        switch (valor.toLowerCase()) {
            case "p":
                return PEQUENO;
            case "m":
                return MEDIO;
            case "g":
                return GRANDE;
            default:
                return null;
        }
    }
}
