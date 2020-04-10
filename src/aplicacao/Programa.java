package aplicacao;

import java.util.Scanner;

import tabuleiro.excecao.TabuleiroExcecao;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
			
			while (true) {
				IU.exibirTabuleiro(partidaDeXadrez.obterPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = IU.lerPosicaoXadrez(sc);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = IU.lerPosicaoXadrez(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMovimentoXadrez(origem, destino);
			}
		} 
		catch (TabuleiroExcecao e) {
			System.out.println();
			System.out.println("Aviso:" + e.getMessage());
		}
	}

}
