package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private boolean check; // por padr�o uma variavel booleana recebe false
	private boolean checkMate;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCatpuradas = new ArrayList<>();

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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		//converter as duas posi��es para posi��es da matriz
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		
		if (testarCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezExcecao("Voce nao pode se colocar em check!!!");
		}
		
		check = (testarCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testarCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		p.incrementaContadorDeMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCatpuradas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		p.decrementaContadorDeMovimento();
		tabuleiro.colocarPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCatpuradas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
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
		//Condi��o ternaria: caso jogador atual seja o branco ? move preto : sen�o move branco
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}

	private PecaDeXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o rei da cor " + cor + "no tabuleiro.");
	}
	
	private boolean testarCheck(Cor cor) {
		Posicao posicaoDoRei = rei(cor).obterPosicaoXadrez().paraPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasDoOponente) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testarCheckMate(Cor cor) {
		if (!testarCheck(cor)) {
			return false;
		}
		List <Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			boolean [][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if(matriz[i][j]) {
						Posicao origem = ((PecaDeXadrez)p).obterPosicaoXadrez().paraPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = realizarMovimento(origem, destino);
						boolean testeCheck = testarCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeCheck) {
							return false;
						}	
					}				
				}
			}
		}
		return true;
	}
	
	private void colocarPecaNova(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	//Metodo repons�vel pela disposi��o inicial das pe�as
	private void setupInicial() {
		//Pe�as pretas
		colocarPecaNova('a', 8, new Torre(tabuleiro, Cor.PRETA)); 
//		colocarPecaNova('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarPecaNova('c', 8, new Bispo(tabuleiro, Cor.PRETA));
//		colocarPecaNova('d', 8, new Rainha(tabuleiro, Cor.PRETA));  
		colocarPecaNova('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarPecaNova('f', 8, new Bispo(tabuleiro, Cor.PRETA));
//		colocarPecaNova('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
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
//		colocarPecaNova('b', 1, new Cavalo(tabuleiro, Cor.BRANCA)); 
		colocarPecaNova('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
//		colocarPecaNova('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocarPecaNova('e', 1, new Rei(tabuleiro, Cor.BRANCA));
		colocarPecaNova('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
//		colocarPecaNova('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
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
