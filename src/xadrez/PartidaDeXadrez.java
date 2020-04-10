package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.enumeracao.Cor;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		this.tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaDeXadrez[][] obterPecas() {
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	private void colocarPecaNova(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	//Metodo repons�vel pela disposi��o inicial das pe�as
	private void setupInicial() {
		//Pe�as pretas
		colocarPecaNova('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarPecaNova('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarPecaNova('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocarPecaNova('d', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarPecaNova('e', 8, new Rainha(tabuleiro, Cor.PRETA));
		colocarPecaNova('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocarPecaNova('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarPecaNova('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarPecaNova('a', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('b', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('c', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('d', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('e', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('f', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('g', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarPecaNova('h', 7, new Peao(tabuleiro, Cor.PRETA));
		//Pe�as brancas
		colocarPecaNova('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarPecaNova('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		colocarPecaNova('e', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocarPecaNova('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarPecaNova('a', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('d', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('e', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('f', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('g', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('h', 2, new Peao(tabuleiro, Cor.BRANCA));
	}
}