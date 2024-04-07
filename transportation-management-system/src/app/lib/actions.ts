"use server";

export async function authenticate(formData: FormData) {
   console.log(
    formData.get("password"),
    formData.get("email")
   );
  
    return true;
  }
  