package aplicacao;

import tabuleiro.excecao.TabuleiroExcecao;
import xadrez.PartidaDeXadrez;

public class Programa {

	public static void main(String[] args) {
		
		try {
			PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
			IU.exibirTabuleiro(partidaDeXadrez.obterPecas());
		} 
		catch (TabuleiroExcecao e) {
			System.out.println("Aviso:" + e.getMessage());
		}
	}

}
