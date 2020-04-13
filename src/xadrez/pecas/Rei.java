package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.enumeracao.Cor;

public class Rei extends PecaDeXadrez{
	
	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean possoMover(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeRockTorre (Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
 	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Cima
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Superior Direita
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita
		p.receberValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Inferior Direita
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Inferior Esquerda
		p.receberValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda
		p.receberValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//Diagonal Superior Esquerda
		p.receberValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && possoMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//movimento especial Rock
		if (getContadorMovimento() == 0 && !partidaDeXadrez.getCheck()) {
			//rock do lado do rei (rock pequeno)
			Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeRockTorre(posicaoTorre1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			//rock do lado da rainha (rock grande)
			Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testeRockTorre(posicaoTorre2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return matriz;
	}
}
