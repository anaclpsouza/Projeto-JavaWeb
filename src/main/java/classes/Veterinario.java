package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Veterinario {

	private String nome, telefone, user, senha;
	private int idade, id;
	PreparedStatement pst = null;
	
	public Veterinario() {
		
	}
	public Veterinario(String nome, String telefone, int idade, String user, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.user = user;
		this.senha = senha;
	}
	
	public Veterinario(int id, String nome, String telefone, int idade, String user, String senha) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.user = user;
		this.senha = senha;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean saveVet() {
		String sql = "insert into veterinario (nome, idade, telefone, usuario, senha) value (?, ?, ?, ?, ?)";
		BD.con = BD.conectar();
		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setInt(2, this.getIdade());
			pst.setString(3, this.getTelefone());
			pst.setString(4, this.getUser());
			pst.setString(5, this.getSenha());
			pst.executeUpdate();
			System.out.print("Veterinário cadastrado");
			return true;
		} catch (SQLException ex) {
			System.out.print("Não adicionou. " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateVet(int id) {
		String sql = "update veterinario set nome=?, idade=?, telefone=?, usuario=?, senha=? where id=?";
		BD.con = BD.conectar();
		boolean editado = false;
		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setInt(2, this.getIdade());
			pst.setString(3, this.getTelefone());
			pst.setString(4, this.getUser());
			pst.setString(5, this.getSenha());
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
	
	public boolean deleteVet(int id) {
	    String deleteAnimalsSql = "DELETE FROM animal WHERE id_veterinario = ?";
	    String deleteVetSql = "DELETE FROM veterinario WHERE id = ?";
	    BD.con = BD.conectar();

	    try {
	        pst = BD.con.prepareStatement(deleteAnimalsSql);
	        pst.setInt(1, id);
	        pst.executeUpdate();

	        pst = BD.con.prepareStatement(deleteVetSql);
	        pst.setInt(1, id);
	        pst.executeUpdate();

	        System.out.println("Veterinário e animais relacionados excluídos com sucesso.");
	        return true;
	    } catch (SQLException e) {
	        System.out.println("Erro ao excluir o veterinário e seus animais: " + e.getMessage());
	        return false;
	    } finally {
	        BD.desconectar();
	    }
	}

	public ArrayList<Veterinario> getAll() {
		String sql = "select * from veterinario";
		ArrayList<Veterinario> vets = new ArrayList<>();
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Nenhum dado encontrado.");
			} else {
				while (rs.next()) {
					vets.add(new Veterinario(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getString(5),
							rs.getString(6)));
				}
			}
			System.out.println("Donos encontrados: " + vets.size());
			for (Veterinario vet : vets) {
				System.out.println(vet.getNome());
			}
		} catch (SQLException e) {
			System.out.println("Erro do GetAll : " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return vets;
	}
	
	public Veterinario getVet(int id) {
		String sql = "select * from veterinario where id=?";
		Veterinario vet = null;
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				vet = new Veterinario(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getString(5),
						rs.getString(6));
			} else {
				System.out.println("Nenhum dado encontrado para o ID: " + id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o dono: " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return vet;
	}
	
	public ArrayList<Animal> getAnimaisVet(int idVet) {
	    String sql = "SELECT * FROM animal WHERE id_veterinario = ?";
	    ArrayList<Animal> pets = new ArrayList<>();
	    BD.con = BD.conectar();
	    ResultSet rs = null;

	    try {
	        pst = BD.con.prepareStatement(sql);
	        pst.setInt(1, idVet);
	        rs = pst.executeQuery();

	        if (!rs.isBeforeFirst()) {
	            System.out.println("Nenhum animal encontrado para o veterinário com id " + idVet);
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
