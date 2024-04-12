"use client";

import Link from "next/link";
import Input from "../../components/forms/input";
import Label from "../../components/forms/label";
import Button from "../../components/forms/button";
import { FormEvent, useRef, useState } from "react";
import { FaEye, FaEyeSlash, FaRegRegistered, FaUpload } from "react-icons/fa";
import FileInput from "../../components/forms/fileInput";
import Selected from "../../components/selected";
import { IoMdSend } from "react-icons/io";
import { postData } from "@/services/postData";

export default function RegisterForm() {
    const [isPasswordVisible, setIsPasswordVisible] = useState(false);
    const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] = useState(false);
    const inputRefs = useRef<(HTMLInputElement | HTMLSelectElement)[]>([]);

    const onSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const formData = new FormData();
        inputRefs.current.forEach((ref) => {
            if (ref instanceof HTMLInputElement) {
                if (ref.type === 'file' && ref.files) {
                    const files = ref.files[0];
                    formData.append("photo", files);
                } else {
                    formData.append(ref.name, ref.value);
                }
            } else if (ref instanceof HTMLSelectElement) {
                formData.append(ref.name, ref.value);
            }
        });
        try {
            const response = await postData("/auth/register", formData);
            console.log(response);
        } catch (error) {
            console.error("Une erreur s'est produite lors de l'envoi des données :", error);
        }
    };



    return (
        <>
            <div className="flex items-center justify-center h-screen w-full  bg-image">
                <form onSubmit={onSubmit} className="flex items-center bg-slate-600 justify-between flex-col flex-nowrap gap-8 p-6 rounded-lg shadow-sm shadow-gray-700">
                    <div className="flex items-center  justify-center flex-row flex-nowrap">
                        <FaRegRegistered className="w-12 h-12 text-gray-300 border-1" />
                    </div>
                    <div className="flex items-center  justify-between flex-row flex-nowrap gap-3 w-full">
                        <div>
                            <Label
                                htmlFor="lastName"
                                text="Nom"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="lastName"
                                placeholder="Nom"
                                autoFocus={true}
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="firstName"
                                text="Prénom"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="firstName"
                                placeholder="Prénom"
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                        </div>
                    </div>
                    <div className="flex items-center  justify-between flex-row flex-nowrap gap-3 w-full">
                        <div>
                            <Label
                                htmlFor="phoneNumber"
                                text="Télèphone"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="phoneNumber"
                                placeholder="Télèphone"
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="email"
                                text="E-mail"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="email"
                                placeholder="email"
                                type="email"
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                        </div>
                    </div>
                    <div className="flex items-center  justify-between flex-row flex-nowrap gap-3 w-full">

                        <div className="relative w-1/3">
                            <Label
                                htmlFor="password"
                                text="Mot de passe"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="password"
                                placeholder="Mot de passe"
                                type={isPasswordVisible ? "text" : "password"}
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                            <span onClick={() => setIsPasswordVisible(!isPasswordVisible)} className="absolute right-2 top-9 text-blue-500">
                                {isPasswordVisible ? <FaEyeSlash /> : <FaEye />}
                            </span>
                        </div>

                        <div className="relative  w-1/3 mr-6">
                            <Label
                                htmlFor="confirmPassword"
                                text="Confirmation de mot de passe"
                                className="text-[12px] font-bold"
                            />
                            <Input
                                name="confirmPassword"
                                placeholder="confirmer de mot de passe"
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                type={isConfirmPasswordVisible ? "text" : "password"}
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                            <span onClick={() => setIsConfirmPasswordVisible(!isConfirmPasswordVisible)} className="absolute right-2 top-9 text-blue-500">
                                {isConfirmPasswordVisible ? <FaEyeSlash /> : <FaEye />}
                            </span>
                        </div>
                    </div>
                    <div className="flex items-center  justify-between flex-row flex-nowrap gap-8 w-full">
                        <div>
                            <Label
                                htmlFor="gender"
                                text="Sexe"
                                className="text-[12px] font-bold"
                            />
                            <Selected
                                data={{ M: "Homme", F: "Femme", OTHER: "Autre" }}
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                name="gender"
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />

                        </div>
                        <div className="relative w-1/2">
                            <label htmlFor="file">
                                <span className="absolute flex justify-center items-center text-[11px] top-6 bottom-0 left-0 w-40 bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1">
                                    <FaUpload className="inline-block mr-2" />
                                </span>
                            </label>
                            <Label
                                htmlFor="file"
                                text="Choisissez une image"
                                className="text-[12px] font-bold"
                            />
                            <FileInput
                                ref={(el) => { if (el) inputRefs.current.push(el); }}
                                name="file"
                                className="w-full bg-slate-500 text-black py-2 px-3 outline-none focus:outline-gray-500 rounded-sm outline-1"
                            />
                        </div>
                    </div>

                    <div className="flex flex-row space-x-14 w-full justify-evenly py-4">
                        <span className="text-[12px]">Avez-vous déjà un compte??</span>
                        <Link href="/login" className="text-[12px] text-blue-500 hover:underline">avoir un compte</Link>
                    </div>

                    <Button
                        type="submit"
                        className="w-full mx-5 flex items-center justify-center bg-blue-500 rounded hover:bg-blue-700  p-4 text-white text-center"
                        text={<IoMdSend />}
                    />
                </form>
            </div>
        </>
    )
}