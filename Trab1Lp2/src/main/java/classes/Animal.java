package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Animal {
	private int id, id_dono, id_vet;
	private String nome, raca;
	private int idade;
	private double peso;
	public PreparedStatement pst = null;

	public Animal(int id, String nome, String raca, int idade, double peso, int id_dono, int id_vet) {
		super();
		this.id = id;
		this.id_dono = id_dono;
		this.id_vet = id_vet;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.idade = idade;
	}

	public Animal(int id_dono, int id_vet, String nome, String raca, double peso, int idade) {
		super();
		this.id_dono = id_dono;
		this.id_vet = id_vet;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.idade = idade;
	}

	public Animal() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_dono() {
		return id_dono;
	}

	public void setId_dono(int id_dono) {
		this.id_dono = id_dono;
	}

	public int getId_vet() {
		return id_vet;
	}

	public void setId_vet(int id_vet) {
		this.id_vet = id_vet;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean savePet() {
		String sql = "insert into animal (nome, raca, idade, peso, id_dono, id_veterinario) value (?, ?, ?, ?, ?, ?)";
		BD.con = BD.conectar();
		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setString(2, this.getRaca());
			pst.setInt(3, this.getIdade());
			pst.setDouble(4, this.getPeso());
			pst.setInt(5, this.getId_dono());
			pst.setInt(6, this.getId_vet());
			pst.executeUpdate();
			System.out.print("Animal cadastrado");
			return true;
		} catch (SQLException ex) {
			System.out.print("Não adicionou. " + ex.getMessage());
			return false;
		}
	}

	public ArrayList<Animal> getAll() {
		String sql = "select * from animal";
		ArrayList<Animal> pets = new ArrayList<>();
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Nenhum dado encontrado.");
			} else {
				while (rs.next()) {
					pets.add(new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
							rs.getInt(6), rs.getInt(7)));
				}
			}
			System.out.println("Donos encontrados: " + pets.size());
			for (Animal pet : pets) {
				System.out.println(pet.getNome());
			}
		} catch (SQLException e) {
			System.out.println("Erro do GetAll : " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return pets;
	}

	public boolean deleteAnimal(int id) {
		BD.con = BD.conectar();
		String sql = "delete from animal where id= ?";

		try {
			pst = BD.con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			BD.desconectar();
		}

		return false;
	}

	public boolean updatePet(int id) {
		BD.con = BD.conectar();
		String sql = "update animal set nome = ?, raca = ?,idade = ?, peso = ?, id_dono = ?, id_veterinario = ? where id= ?";

		try {
			pst = BD.con.prepareStatement(sql);
			pst.setString(1, this.getNome());
			pst.setString(2, this.getRaca());
			pst.setInt(3, this.getIdade());
			pst.setDouble(4, this.getPeso());
			pst.setInt(5, this.getId_dono());
			pst.setInt(6, this.getId_vet());
			pst.setInt(7, id);
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			BD.desconectar();
		}

		return false;
	}

	public Animal getAnimal(int id) {
		String sql = "select * from animal where id=?";
		Animal pet = null;
		BD.con = BD.conectar();
		ResultSet rs = null;

		try {
			pst = BD.con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				pet = new Animal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
						rs.getInt(6), rs.getInt(7));
			} else {
				System.out.println("Nenhum dado encontrado para o ID: " + id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o animal: " + e.getMessage());
		} finally {
			BD.desconectar();
		}
		return pet;
	}
}
