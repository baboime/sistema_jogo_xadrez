package tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	//Associa��o - a pe�a est� em um tabuleiro
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
