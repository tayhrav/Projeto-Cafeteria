public enum Tamanho {
    PEQUENO,
    MEDIO,
    GRANDE;

    @Override
	public String toString() {
		return this.name().toLowerCase();
	}

    public static Tamanho obterTamanho(String valor) {
        switch (valor.toLowerCase()) {
            case "p":
                return PEQUENO;
            case "m":
                return MEDIO;
            default:
                return GRANDE;
        }
    }
}
