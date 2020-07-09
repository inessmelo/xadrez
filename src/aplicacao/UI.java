package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import jogoTabuleiro.PosicaoJogo;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PecasXadrez;
import xadrez.PosicaoXadrez;

public class UI {
        
    //CORES DAS LETRAS
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //CORES DE FUNDO
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static PosicaoXadrez lerPosicaoXadrez(Scanner dados){
        try{
            String s = dados.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Erro lendo XadrezPosição: valores válidos são de a1 a h8");
        }
    }
    
    public static void imprimePartida(PartidaXadrez partida, List<PecasXadrez> capturada){
        imprimeTabuleiro(partida.pegarPecas());
        System.out.println();
        imprimeCapturaPecas(capturada);
        System.out.println();
        System.out.println("Rodada: " + partida.getVirar());
        if(!partida.getCheckMate()){
            System.out.println("Esperando jogador: " + partida.getJogadorAtual());
            if(partida.getCheck()){
                System.out.println("CHECK!");
            }
        }
        else{
            System.out.println("CHECKMATE!");
            System.out.println("Vencedor: " + partida.getJogadorAtual());
        }
    } 
    
    public static void imprimeTabuleiro(PecasXadrez[][] pecas){
        for (int i = 0; i < pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++){
                imprimePeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    
    public static void imprimeTabuleiro(PecasXadrez[][] pecas, boolean [][] possivelMovimento){
        for (int i = 0; i < pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++){
                imprimePeca(pecas[i][j], possivelMovimento[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    
    private static void imprimePeca(PecasXadrez peca, boolean fundo){
        if(fundo){
            System.out.print(ANSI_CYAN_BACKGROUND);
        }
        if(peca == null){
            System.out.print("- " + ANSI_RESET);
        }
        else{
            if (peca.getCor() == Cores.BRANCO){
                System.out.print(ANSI_RED + peca + ANSI_RESET);
            }
            else{
                System.out.print(ANSI_BLACK + peca + ANSI_RESET);
            }
        System.out.print(" ");
        }
    }
    
    private static void imprimeCapturaPecas(List<PecasXadrez> capturada){
        List<PecasXadrez> branco = capturada.stream().filter(x -> x.getCor() == Cores.BRANCO).collect(Collectors.toList());
        List<PecasXadrez> preta = capturada.stream().filter(x -> x.getCor() == Cores.PRETO).collect(Collectors.toList());
        System.out.println("Captura de peças");
        
        System.out.print("Brancas: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(branco.toArray()));
        System.out.print(ANSI_RESET);
        
        System.out.print("Pretas: ");
        System.out.print(ANSI_BLACK);
        System.out.println(Arrays.toString(preta.toArray()));
        System.out.print(ANSI_RESET);        
    }
}
