package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import tabuleiro.excecao.TabuleiroExcecao;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.excecao.XadrezExcecao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
			
			while (true) {
				try {
					IU.limparTela();
					IU.exibirTabuleiro(partidaDeXadrez.obterPecas());
					System.out.println();
					System.out.print("Origem: ");
					PosicaoXadrez origem = IU.lerPosicaoXadrez(sc);
					
					boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
					IU.limparTela();
					IU.exibirTabuleiro(partidaDeXadrez.obterPecas(), movimentosPossiveis);
					
					System.out.println();
					System.out.print("Destino: ");
					PosicaoXadrez destino = IU.lerPosicaoXadrez(sc);
					
					PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMovimentoXadrez(origem, destino);
				}
				catch(XadrezExcecao e) {
					System.out.println();
					System.out.println(e.getMessage());
					//Programa aguarda pressionar <Enter> para encerrar
					sc.nextLine();
				}
				catch(InputMismatchException e) {
					System.out.println();
					System.out.println(e.getMessage());
					//Programa aguarda pressionar <Enter> para encerrar
					sc.nextLine();
				}
			}
		} 
	}
