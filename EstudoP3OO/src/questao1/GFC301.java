package questao1;

class GFC301 {
	private String nome;

	public GFC301(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static void m1(GFC301 r1, GFC301 r2) {
		r1.setNome("passaro");
		r2 = r1;
	}

	public static void main(String[] args) {
		GFC301 pet1 = new GFC301("cachorro");
		GFC301 pet2 = new GFC301("gato");
		m1(pet1, pet2);
		System.out.println(pet1.getNome() + "," + pet2.getNome());
	}
}
