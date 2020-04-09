package tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	//Associação - a peça está em um tabuleiro
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
