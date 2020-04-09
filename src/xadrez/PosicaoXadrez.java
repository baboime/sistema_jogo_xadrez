package xadrez;

import tabuleiro.Posicao;
import xadrez.excecao.XadrezExcecao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 0 || linha > 8) {
			throw new XadrezExcecao("Erro ao instanciar a PosicaoXadrez. Valores válidos são de a1 para h8 [" + coluna + "," + linha + "]");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public char getColuna() {
		return coluna;
	}
	public int getLinha() {
		return linha;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez daPosicao(Posicao posicao) {
		return new PosicaoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());	
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}
