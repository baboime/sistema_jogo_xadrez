package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.enumeracao.Cor;

public class Peao extends PecaDeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCA) {
			p.receberValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;		
			}
			p.receberValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existeUmaPeca(p2) && getContadorMovimento() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;		
			}
			
			//peca oponente diagonal esquerda
			p.receberValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			//peca oponente diagonal direita
			p.receberValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.receberValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;		
			}
			p.receberValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existeUmaPeca(p2) && getContadorMovimento() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;		
			}
			
			//peca oponente diagonal esquerda
			p.receberValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			//peca oponente diagonal direita
			p.receberValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		return matriz;
	}
}
