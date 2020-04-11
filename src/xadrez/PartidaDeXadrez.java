package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.enumeracao.Cor;
import xadrez.excecao.XadrezExcecao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		this.tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaDeXadrez executaMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		//converter as duas posições para posições da matriz
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		proximoTurno();
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezExcecao("Nao ha peca na posicao origem informada!");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("Peca escolhida nao e sua!");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new XadrezExcecao("Nao existe movimento possivel para a peca escolhida!");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new XadrezExcecao("A peca escolhida nao pode ser movida para a posicao destino!");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		//Condição ternaria: caso jogador atual seja o branco ? move preto : senão move branco
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private void colocarPecaNova(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	//Metodo reponsável pela disposição inicial das peças
	private void setupInicial() {
		//Peças pretas
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
		//Peças brancas
		colocarPecaNova('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarPecaNova('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		colocarPecaNova('e', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocarPecaNova('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarPecaNova('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarPecaNova('c', 3, new Peao(tabuleiro, Cor.BRANCA));  //ajustar para posicao a2
		colocarPecaNova('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('e', 3, new Peao(tabuleiro, Cor.BRANCA));  //ajustar para posicao d2
		colocarPecaNova('e', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('f', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('g', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarPecaNova('h', 2, new Peao(tabuleiro, Cor.BRANCA));
	}
}
