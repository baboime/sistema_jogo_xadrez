package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.enumeracao.Cor;

public abstract class PecaDeXadrez extends Peca{
	
	private Cor cor;
	private int contadorMovimento;
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public PosicaoXadrez obterPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}

	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	public void setContadorMovimento(int contadorMovimento) {
		this.contadorMovimento = contadorMovimento;
	}

	protected boolean existePecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
	
	protected void incrementaContadorDeMovimento() {
		contadorMovimento++;
	}
	
	protected void decrementaContadorDeMovimento() {
		contadorMovimento--;
	}
}
