"use client";

import Link from "next/link";
import Input from "../../components/forms/input";
import Label from "../../components/forms/label";
import Button from "../../components/forms/button";
import { MdLogin } from "react-icons/md";
import { VscAccount } from "react-icons/vsc";
import { FormEvent, useRef, useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { postData } from "@/services/postData";

export default function LoginForm() {
    const [isPasswordVisible, setIsPasswordVisible] = useState(false);

    const inputRefs = useRef<HTMLInputElement[]>([]);

    const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const formData: { [key: string]: string } = {};
        inputRefs.current.forEach((ref) => {
            formData[ref.name] = ref.value;
        });
        try {
            const response = await postData("/auth/login", formData);
            console.log(response);
        } catch (error) {
            console.error("Une erreur s'est produite lors de l'envoi des données :", error);
        }

    }


    return (
        <>
            <div className="flex items-center justify-center  h-screen bg-image">
                <form onSubmit={onSubmit} className="flex items-center bg-slate-600 justify-between flex-col flex-nowrap gap-7 p-6 rounded-lg shadow-sm shadow-gray-700">
                    <div className="flex items-center  justify-center flex-row flex-nowrap">
                        <VscAccount className="w-12 h-12 text-gray-300 border-1" />
                    </div>
                    <div className="w-full">
                        <Label
                            htmlFor="email"
                            text="E-mail"
                            className="text-[12px] font-bold"
                        />
                        <Input
                            name="email"
                            placeholder="email"
                            autoFocus={true}
                            type="email"
                            ref={(el) => { if (el) inputRefs.current.push(el); }}
                            className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                        />
                    </div>
                    <div className="relative w-full">
                        <Label
                            htmlFor="password"
                            text="Mot de passe"
                            className="text-[12px] font-bold"
                        />
                        <Input
                            name="password"
                            placeholder="Mot de passe"
                            ref={(el) => { if (el) inputRefs.current.push(el); }}
                            type={isPasswordVisible ? "text" : "password"}
                            className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                        />
                        <span onClick={() => setIsPasswordVisible(!isPasswordVisible)} className="absolute right-2 top-9 text-blue-500">
                            {isPasswordVisible ? <FaEyeSlash /> : <FaEye />}
                        </span>
                    </div>
                    <div className="flex flex-row space-x-14">
                        <span className="text-[12px]">Vous n'avez pas encore de compte?</span>
                        <Link href="/register" className="text-[12px] text-blue-500 hover:underline">créer un compte</Link>
                    </div>
                    <div className="mx-5 flex items-center justify-center bg-blue-500 rounded hover:bg-blue-700 w-1/2 p-4">
                        <Button
                            type="submit"
                            className="text-white text-center"
                            text={<MdLogin />}
                        />
                    </div>
                </form>
            </div>
        </>
    )
}