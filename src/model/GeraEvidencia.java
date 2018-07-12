package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GeraEvidencia {

	private Scanner leitorArquivo;
	private List<String> listaArquivos = new ArrayList<String>();
	private String campo;
	private String arquivo;
	private boolean envio = false;

	public void processar() {

		try {
			this.leitorArquivo = new Scanner(new FileReader("entrada.txt"));

			while (this.leitorArquivo.hasNext()) {
				this.montarLista();
			}

			if (this.listaArquivos.size() > 0) {
				this.copiarEvidencia();
				this.envio = true;
			} else {
				this.envio = false;
			}

			if (this.envio == true) {
				JOptionPane.showMessageDialog(null, "Processamento Concluído.");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void copiarEvidencia(){

		File origem = new File("modelo.docx");

		try {
			FileInputStream fis = new FileInputStream(origem);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (String a : this.listaArquivos) {
			arquivo =  a + "-SCC3" + a + "-ETU.doc";
			File destino = new File(arquivo);
			try {
				FileOutputStream fos = new FileOutputStream(destino);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void montarLista() {
		try {
			this.campo = (String) this.leitorArquivo.nextLine();
			this.listaArquivos.add(this.campo.trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao montar lista.");
		}
	}

	private void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
}
