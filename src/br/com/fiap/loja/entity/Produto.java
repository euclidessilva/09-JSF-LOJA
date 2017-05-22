package br.com.fiap.loja.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TBL_PRODUTO")
@SequenceGenerator(name="sqProduto", sequenceName="SEQ_PRODUTO", allocationSize=1)
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator="sqProduto", strategy=GenerationType.SEQUENCE)
	private int cd_produto;
	
	
	private String nm_produto, ds_produto;
	private float vl_preco;
	
	public int getCd_produto() {
		return cd_produto;
	}
	public void setCd_produto(int cd_produto) {
		this.cd_produto = cd_produto;
	}
	public String getNm_produto() {
		return nm_produto;
	}
	public void setNm_produto(String nm_produto) {
		this.nm_produto = nm_produto;
	}
	public String getDs_produto() {
		return ds_produto;
	}
	public void setDs_produto(String ds_produto) {
		this.ds_produto = ds_produto;
	}
	public float getVl_preco() {
		return vl_preco;
	}
	public void setVl_preco(float vl_preco) {
		this.vl_preco = vl_preco;
	}
	
}
