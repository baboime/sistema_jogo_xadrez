package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.enumeracao.Cor;

public class Peao extends PecaDeXadrez{
	
	private PartidaDeXadrez partidaDeXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
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
			
			// movimento especial en passant
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existeUmaPeca(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulnerabilidade()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existeUmaPeca(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulnerabilidade()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
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
			
			// movimento especial en passant
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().existeUmaPeca(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulnerabilidade()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().existeUmaPeca(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulnerabilidade()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return matriz;
	}
}
