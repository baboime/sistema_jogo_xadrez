package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.enumeracao.Cor;

public class Cavalo extends PecaDeXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean possoMover(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Cima movimento a direita
		p.receberValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Cima movimento a esquerda
		p.receberValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita movimento para cima
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita movimento para baixo
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo movimento para direita
		p.receberValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo movimento para esquerda
		p.receberValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda movimento para baixo
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda movimento para cima
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		return matriz;
	}
}
