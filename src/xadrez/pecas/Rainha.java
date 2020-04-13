package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.enumeracao.Cor;

public class Rainha extends PecaDeXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Cima
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Superior Direita
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.receberValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Superior Esquerda
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.receberValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Inferior Esquerda
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.receberValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		//Esquerda
		p.receberValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Inferior Direita
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.receberValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		//Direita
		p.receberValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}
}
