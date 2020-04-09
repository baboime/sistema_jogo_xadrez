package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;
import xadrez.enumeracao.Cor;

public class PecaDeXadrez extends Peca{
	
	private Cor cor;
	private int contadorMovimento;
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
		this.contadorMovimento = 0;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContadorMovimento() {
		return contadorMovimento;
	}
}
