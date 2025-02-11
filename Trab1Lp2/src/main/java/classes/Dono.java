package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dono {
	private String cpf, nome, telefone;
	private String endereco;
	private int idade;
	private int id;
	public PreparedStatement pst = null;

	public Dono() {

	}

	public Dono(String nome, int idade, String telefone, String cpf, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Dono(int id, String nome, int idade, String telefone, String cpf, String endereco) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.cpf = cpf;
		this.endereco = endereco;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean saveDono() {
		String sql = "insert into dono (nome, idade, telefone, cpf, endereco) value (?, ?, ?, ?, ?)";
		BD.con = BD.conectar();
		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setInt(2, this.getIdade());
			pst.setString(3, this.getTelefone());
			pst.setString(4, this.getCpf());
			pst.setString(5, this.getEndereco());
			pst.executeUpdate();
			System.out.print("Dono cadastrado");
			return true;
		} catch (SQLException ex) {
			System.out.print("Não adicionou. " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateDono(int id) {
		String sql = "update dono set nome=?, idade=?, telefone=?, cpf=?, endereco=? where id=?";
		BD.con = BD.conectar();
		boolean editado = false;
		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setInt(2, this.getIdade());
			pst.setString(3, this.getTelefone());
			pst.setString(4, this.getCpf());
			pst.setString(5, this.getEndereco());
			pst.setInt(6, id);
			pst.executeUpdate();
			editado = true;
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return editado;
	}
	
	public boolean deleteDono(int id) {
	    String deleteAnimalsSql = "DELETE FROM animal WHERE id_dono = ?";
	    String deleteDonoSql = "DELETE FROM dono WHERE id = ?";
	    BD.con = BD.conectar();

	    try {
	        pst = BD.con.prepareStatement(deleteAnimalsSql);
	        pst.setInt(1, id);
	        pst.executeUpdate();

	        pst = BD.con.prepareStatement(deleteDonoSql);
	        pst.setInt(1, id);
	        pst.executeUpdate();

	        System.out.println("Dono e animais relacionados excluídos com sucesso.");
	        return true;
	    } catch (SQLException e) {
	        System.out.println("Erro ao excluir o dono e seus animais: " + e.getMessage());
	        return false;
	    } finally {
	        BD.desconectar();
	    }
	}


	public ArrayList<Dono> getAll() {
		String sql = "select * from dono";
		ArrayList<Dono> donos = new ArrayList<>();
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Nenhum dado encontrado.");
			} else {
				while (rs.next()) {
					donos.add(new Dono(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
							rs.getString(6)));
				}
			}
			System.out.println("Donos encontrados: " + donos.size());
			for (Dono dono : donos) {
				System.out.println(dono.getNome());
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return donos;
	}
	
	public Dono getDono(int id) {
		String sql = "select * from dono where id=?";
		Dono dono = null;
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				dono = new Dono(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			} else {
				System.out.println("Nenhum dado encontrado para o ID: " + id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o dono: " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return dono;
	}
	
	public ArrayList<Animal> getAnimaisDono(int idDono) {
	    String sql = "SELECT * FROM animal WHERE id_dono = ?";
	    ArrayList<Animal> pets = new ArrayList<>();
	    BD.con = BD.conectar();
	    ResultSet rs = null;

	    try {
	        pst = BD.con.prepareStatement(sql);
	        pst.setInt(1, idDono);
	        rs = pst.executeQuery();

	        if (!rs.isBeforeFirst()) {
	            System.out.println("Nenhum animal encontrado para o veterinário com id " + idDono);
	        } else {
	            while (rs.next()) {
	                pets.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
							rs.getInt(6), rs.getInt(7)));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar os animais: " + e.getMessage());
	    } finally {
	        BD.desconectar();
	    }

	    return pets;
	}

}
