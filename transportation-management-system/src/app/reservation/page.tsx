
import { FaSearch } from 'react-icons/fa';

export default function Reservation() {
  return (
    <div>
      <form className="flex flex-row justify-center items-center gap-4 
      w-full h-80 p-4 bg-slate-600 text-slate-400">
        <label htmlFor="depart" className='w-64'> DEPART
          <select name="depart"
            id="depart"
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
        </label>

        <label htmlFor="arrivee" className='w-64'>ARRIVEE
          <select name="arrivee" id="arrivee"
            className="text-slate-400 w-full p-2  border border-gray-400 rounded bg-slate-500 
          outline-none focus:outline-gray-500 outline-1">
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
        </label>
        <label htmlFor="date" className='w-64'>DATE
          <input type="date"
            className="p-2 w-full  border border-gray-400 
          rounded bg-slate-500 outline-none focus:outline-gray-500 
          outline-1 text-slate-400" />
        </label>
        <label htmlFor="number" className='w-64'>NUMBER
          <input type="number" placeholder="VOYAGEUR(S)"
            className="p-2 w-full border border-gray-400 rounded bg-slate-500 
          outline-none focus:outline-gray-500 outline-1 " />
        </label>

        <button type="submit" className="bg-blue-500 text-white p-2 rounded-md relative top-3 w-20 flex justify-center">
          <FaSearch />
        </button>
      </form>
    </div>
  )
}
