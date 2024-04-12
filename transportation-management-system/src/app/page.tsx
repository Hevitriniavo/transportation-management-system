"use client";
import { FormEvent, useState } from "react";

export default function Home() {
  const [formSubmitted, setFormSubmitted] = useState(false);
  const [formulair, setFormulair] = useState({
    depart: "",
    arrivee: "",
    date: "",
    number: ""
  })

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (formulair.depart !== '' && formulair.date !== '' && formulair.arrivee !== '' && formulair.number !== '') {

      e.currentTarget.reset()
    } else {
      console.log('Veuillez remplir tous les champs obligatoires.');
    }
    setFormSubmitted(true);
  };

  return (
    <div className="h-screen image">
      <h1 className="text-white font-extrabold w-7/12 text-8xl text-wrap 
      -rotate-45 absolute top-56 text-center">Bienvenue Chez Trans Tantely</h1>
      <div className="flex flex-col justify-evenly items-center w-1/3 h-96 
      rounded bg-slate-600 position ">
        <h1 className="font-bold text-white">Choisissez votre itinéraire</h1>
        <form
          onSubmit={handleSubmit}
          className="flex flex-col justify-around items-center w-full h-80 p-4">
          <select name="depart"
            id="depart"
            onChange={(e) => setFormulair({ ...formulair, depart: e.target.value })}
            value={formulair.depart}
            className="text-slate-400 p-2 w-full  border border-gray-400 
            rounded bg-slate-500 outline-none focus:outline-gray-500 outline-1">
            <option value="" disabled selected>DEPART</option>
            <option value="Antananarivo">ANTANANARIVO</option>
            <option value="Fianarantsoa">FIANARANTSOA</option>
            <option value="Mahajanga">MAHAJANGA</option>
            <option value="Antsiranana">ANTSIRANANA</option>
            <option value="Toamasina">TOAMASINA</option>
            <option value="Ambilobe">AMBILOBE</option>
            <option value="Toliara">TOLIARA</option>
            <option value="Antsirabe">ANTSIRABE</option>
          </select>

          <select
            name="arrivee" id="arrivee"
            onChange={(e) => setFormulair({ ...formulair, arrivee: e.target.value })}
            value={formulair.arrivee}
            className="text-slate-400 p-2 w-full  border border-gray-400 
            rounded bg-slate-500 outline-none focus:outline-gray-500 outline-1">
            <option value="" disabled selected >ARRIVEE</option>
            <option value="Antananarivo">ANTANANARIVO</option>
            <option value="Fianarantsoa">FIANARANTSOA</option>
            <option value="Mahajanga">MAHAJANGA</option>
            <option value="Antsiranana">ANTSIRANANA</option>
            <option value="Toamasina">TOAMASINA</option>
            <option value="Ambilobe">AMBILOBE</option>
            <option value="Toliara">TOLIARA</option>
            <option value="Antsirabe">ANTSIRABE</option>
          </select>

          <input type="date"
            onChange={(e) => setFormulair({ ...formulair, date: e.target.value })}
            value={formulair.date}
            className="p-2 w-full  border border-gray-400 rounded bg-slate-500 
            outline-none focus:outline-gray-500 outline-1 text-slate-400" />
          <input type="number" placeholder="VOYAGEUR(S)"
            onChange={(e) => setFormulair({ ...formulair, number: e.target.value })}
            value={formulair.number}
            className="p-2 w-full  border border-gray-400 rounded bg-slate-500 
            outline-none focus:outline-gray-500 outline-1 " />
          <button type="submit" className="p-2 w-96 bg-blue-600 rounded-md text-white">Réserver</button>
        </form>
        {formSubmitted && (formulair.depart === '' || formulair.date === '' || formulair.arrivee === '' || formulair.number === '') && (
          <p style={{ color: 'red' }}>Veuillez remplir tous les champs obligatoires.</p>
        )}

      </div>
    </div>
  );
}
