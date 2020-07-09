package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import jogoTabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecasXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExcecao;

public class Programa {
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);
        PartidaXadrez partida = new PartidaXadrez();
        List<PecasXadrez> capturada = new ArrayList<>();
        
        while(!partida.getCheckMate()){
            try{
                UI.limparTela();
                UI.imprimePartida(partida, capturada);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(dados);
                
                boolean[][] possivelMovimento = partida.possiveisMovimentos(origem);
                UI.limparTela();
                UI.imprimeTabuleiro(partida.pegarPecas(), possivelMovimento);
                System.out.println();
                                
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(dados);

                PecasXadrez capturaPeca = partida.executarMovimentoXadrez(origem, destino);
               
                if(capturaPeca != null){
                    capturada.add(capturaPeca);
                }
            }
            catch(XadrezExcecao e){
                System.out.println(e.getMessage());
                dados.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                dados.nextLine();
            }
        }
        UI.limparTela();
        UI.imprimePartida(partida, capturada);
    }
    
}
