import bcrypt from "bcrypt";
import User from "../../modules/user/model/User";

export async function createInitialData() {

  try {
    await User.sync({ force: true });

    let password = await bcrypt.hash("12345", 10);
    
      await User.create({
        name: "User Test 1",
        email: "teste1@gmail.com",
        password: password,
      });

      await User.create({
        name: "User Test 2",
        email: "teste2@gmail.com",
        password: password,
      });
  } catch(err) {
    console.log(err.message);
  }

}